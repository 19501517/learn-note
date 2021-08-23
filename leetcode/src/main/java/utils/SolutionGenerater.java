package utils;

import com.sun.codemodel.*;
import com.test.module.ListNode;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 创建个人标准的leetcode测试框架
 */
public class SolutionGenerater {

    private static final String ASSERT_AND_PRINT_METHOD_NAME = "assertAndPrint";
    private static final String GET_PARAMS_METHOD_NAME = "getParams";

    public static void main(String[] args) throws Exception {
        int title = 4;
        String titleName = "MedianOfTwoSortedArrays".trim().replace(" ", "");
        String solutionMethodName = "findMedianSortedArrays".trim().replace(" ", "");

        Class<?>[] paramsClz = new Class[]{Integer[].class, Integer[].class};
        Class<?> expectedResultClz = ListNode[].class;

        File file = generatePackage(title, titleName);
        JType solutionInterface = generateSolutionInterface(file, titleName, solutionMethodName, paramsClz, expectedResultClz);
        generateTest(file, solutionInterface, titleName, solutionMethodName, paramsClz, expectedResultClz);
    }

    private static File generatePackage(int title, String titleName) throws Exception {
        String packageName = "t" + title + "_" + titleName;
        File base = new File(SolutionGenerater.class.getResource("/").getPath()).getParentFile().getParentFile();
        File packageDir = new File(base, "\\src\\main\\java\\" + packageName);

        if (!packageDir.exists()) {
            FileUtils.forceMkdir(packageDir);
        }
        return packageDir;
    }

    private static JType generateSolutionInterface(File packageDir, String titleName, String solutionMethodName,
                                                   Class<?>[] paramsClz, Class<?> expectResultClz) throws Exception {
        String interfaceName = "I" + titleName;

        // 构建类头信息
        JCodeModel interfaceModel = new JCodeModel();
        JPackage jPackage = interfaceModel._package(packageDir.getName());
        JDefinedClass definedClass = jPackage._interface(JMod.NONE, interfaceName);

        // 方法
        JMethod solutionMethod = definedClass.method(JMod.PUBLIC, expectResultClz, solutionMethodName);
        int count = 1;
        for (Class<?> clz : paramsClz) {
            JVar param = solutionMethod.param(clz, "input" + count++);
        }

        interfaceModel.build(packageDir.getParentFile().getAbsoluteFile());

        return interfaceModel.parseType(interfaceName);
    }

    private static void generateTest(File packageDir, JType solutionInterface, String titleName, String solutionMethodName,
                                     Class<?>[] paramsClz, Class<?> expectResultClz) throws Exception {

        String className = titleName + "Test";

        // 构建类头信息
        JCodeModel codeModel = new JCodeModel();
        JPackage jPackage = codeModel._package(packageDir.getName());
        JDefinedClass definedClass = jPackage._class(JMod.NONE, className);

        createGetParamsMethod(codeModel, definedClass);
        createSolutionTestMethod(codeModel, definedClass, paramsClz, expectResultClz);
        createAssertAndPrintMethod(codeModel, definedClass, solutionInterface,
                solutionMethodName, paramsClz, expectResultClz);
        codeModel.build(packageDir.getParentFile().getAbsoluteFile());
    }

    private static void createGetParamsMethod(JCodeModel codeModel, JDefinedClass definedClass) {
        JClass streamType = codeModel.ref(Stream.class);
        JClass objectType = codeModel.ref(Object.class);
        JMethod method = definedClass.method(JMod.PRIVATE | JMod.STATIC, streamType.narrow(objectType), GET_PARAMS_METHOD_NAME);
        method.body()._return(streamType.staticInvoke("of"));
    }

    private static void createSolutionTestMethod(JCodeModel codeModel, JDefinedClass definedClass, Class<?>[] paramsClz, Class<?> expectResultClz) {
        JMethod method = definedClass.method(JMod.NONE, void.class, "solutionTest");

        // 添加注解
        method.annotate(ParameterizedTest.class);
        method.annotate(MethodSource.class).param("value", GET_PARAMS_METHOD_NAME);
        method.annotate(DisplayName.class).param("value", "solution");

        // 添加方法参数|
        int count = 1;
        for (Class<?> clazz : paramsClz) {
            method.param(clazz, "input" + count++);
        }
        method.param(expectResultClz, "expectedResult");
    }

    private static void createAssertAndPrintMethod(JCodeModel codeModel, JDefinedClass definedClass, JType solutionInterface, String solutionMethodName,
                                                   Class<?>[] paramsClz, Class<?> expectResultClz) throws Exception {
        //开始构建 assertAndPrint方法
        JMethod assertMethod = definedClass.method(JMod.PRIVATE, void.class, ASSERT_AND_PRINT_METHOD_NAME);
        int assertMethodParamsCount = 1;
        List<JVar> inputVars = new ArrayList<>();
        for (Class<?> clz : paramsClz) {
            String paramsName = "input" + assertMethodParamsCount++;
            JVar var = assertMethod.param(clz, paramsName);
            inputVars.add(var);
        }
        JVar expectedResultVar = assertMethod.param(expectResultClz, "expectedResult");
        JVar solutionVar = assertMethod.param(solutionInterface, "solution");

        JType longType = codeModel.parseType("long");
        JClass systemType = codeModel.ref(System.class);
        JClass arraysClass = codeModel.ref(Arrays.class);

        JBlock assertMethodBody = assertMethod.body();

        //long begin = System.currentTimeMillis();
        JVar beginVar = assertMethodBody.decl(longType, "begin");
        beginVar.init(systemType.staticInvoke("currentTimeMillis"));

        //T result = solution.findMedianSortedArrays(var1, var2);
        JInvocation solutionInvoke = solutionVar.invoke(solutionMethodName);
        for (JVar input : inputVars) {
            solutionInvoke.arg(input);
        }
        JVar resultVar = assertMethodBody.decl(expectedResultVar.type(), "result");
        resultVar.init(solutionInvoke);

        //long end = System.currentTimeMillis();
        JVar endVar = assertMethodBody.decl(longType, "end");
        endVar.init(systemType.staticInvoke("currentTimeMillis"));

        // System.out.println("--------------------------------------")
        assertMethodBody.add(systemType.staticRef("out").invoke("println").arg("---------------------------"));


        // 循环打印当前输入的参数
        int inputCount = 1;
        for (JVar input : inputVars) {
            JExpression inputStr = input;
            if (input.type().isArray()) {
                inputStr = arraysClass.staticInvoke("toString").arg(input);
            }
            JExpression printStr = JExpr.lit("given var" + inputCount + " : ").plus(inputStr);
            assertMethodBody.add(systemType.staticRef("out").invoke("println").arg(printStr));
        }

        //System.out.println("expect result : " + actualResult);
        JExpression expectResultStr = JExpr.lit("expect result : ");
        if (expectedResultVar.type().isArray()) {
            expectResultStr = expectResultStr.plus(arraysClass.staticInvoke("toString").arg(expectedResultVar));
        } else {
            expectResultStr = expectResultStr.plus(expectedResultVar);
        }
        assertMethodBody.add(systemType.staticRef("out").invoke("println").arg(expectResultStr));

        //System.out.println("actual result : " + actualResult);
        JExpression actualResultStr = JExpr.lit("actual result : ");
        if (resultVar.type().isArray()) {
            actualResultStr = actualResultStr.plus(arraysClass.staticInvoke("toString").arg(resultVar));
        } else {
            actualResultStr = actualResultStr.plus(resultVar);
        }
        assertMethodBody.add(systemType.staticRef("out").invoke("println").arg(actualResultStr));

        //System.out.println("time : " + time);
        JExpression timeStr = JExpr.lit("time : ").plus(endVar.minus(beginVar));
        assertMethodBody.add(systemType.staticRef("out").invoke("println").arg(timeStr));
    }
}
