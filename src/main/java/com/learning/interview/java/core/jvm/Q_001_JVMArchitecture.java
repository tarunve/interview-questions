package com.learning.interview.java.core.jvm;

import java.lang.reflect.Method;

/**
 *  -   Responsible for 3 activities:
 *      1.  Loading     2.  Linking     3.  Verification
 *  -   While loading, linking and initialization, if any error occurs then we will get
 *      RE: java.lang.LinkageError.
 *
 *  Loading:
 *  =======
 *  -   Loading means reading class files and store corresponding binary data in method area.
 *  -   For each class file, JVM will store corresponding information in the method area:
 *      -   Fully qualified name of class.
 *      -   Fully qualified name of immediate parent class.
 *      -   Method, Variable, Constructor, Modifier, Constant pool information.
 *  -   After loading .class file, immediately JVM creates an object for that loaded class in
 *      the heap memory of type - java.lang.Class
 *  -   The Class - class object can be used by programmer to get class level information like
 *      method info or variable info or constructor info etc.
 *
 *  Linking:
 *  =======
 *  -   Consist of 3 activities:
 *      1.  Verify:
 *          -   Process of ensuring that binary representation of a class is structurally correct
 *              or not i.e. JVM will check whether .class is generated by valid compiler or not.
 *          -   Internally, Bytecode Verifier is responsible for this activity. Bytecode Verifier
 *              is the part of class loader sub-system.
 *          -   If verification fails then we will get RE: java.lang.VerifyError.
 *      2.  Preparation:
 *          -   In this phase, JVM will allocate memory for class level static variables and
 *              assign default values.
 *          NOTE: In Initialization phase, original values would be assigned to static variables.
 *      3.  Resolve/Resolution:
 *          -   Process of replacing symbolic names in our program with original memory reference
 *              from Method area.
 *
 *  Initialization:
 *  ==============
 *  -   In this phase, all static variables are assigned with original values and static blocks
 *      will be executed from parent to child and from top to bottom.
 *
 *  Execution Engine:
 *  ===============
 *  -   Central point of JVM.
 *  -   Responsible to execute Java Class files.
 *  -   Consist of 2 components mainly: 1.  Interpreter 2.  JITCompiler
 *      1.  Interpreter:
 *          -   Responsible to read bytecode and interpret into machine code(native) & execute that
 *              machine code line by line.
 *          -   The problem with interpreter is - It interprets everytime for even same method
 *              invoked multiple times which reduces performance of system.
 *          -   To overcome this problem, sun people introduced JIT compiler in 1.1 version.
 *      2.  JIT Compiler:
 *          -   Main purpose is to improve performance.
 *          -   Internally, JIT compiler maintains a separate count for every method.
 *          -   Whenever JVM come across any method call. First, that method will be interpreted
 *              normally by interpreter and JIT compiler increments count variable.
 *          -   This process will be continued for every method. Once, if any method count reaches
 *              thresh hold value then JIT compiler identifies that, that method is repeatedly used.
 *          -   Immediately JIT compiler compiles that method and generates the corresponding native code.
 *          -   Next time, JVM come across that method call then JVM uses native code directly and
 *              executes it instead of interpreting once again so that performance of the system
 *              will be improved.
 *          -   Thresh hold count varied from JVM to JVM.
 *
 *  JNI (Java Native Interface)
 *  ===========================
 *  -   It acts as mediator for java method calls and corresponding native libraries i.e. JNI
 *      is responsible to provide information about native libraries to JVM.
 *  -   Native method library provides/holds native libraries info.
 *
 *
 *
 */
public class Q_001_JVMArchitecture {

    //Loading
    static class LoadingClass{
        public static void main(String[] args) throws ClassNotFoundException {
            int count = 0;
            Class cls = Class.forName(String.class.getName());
            Method[] methods = cls.getDeclaredMethods();
            for (Method m: methods){
                count++;
                System.out.println(m.getName());
            }
            System.out.println("Number of methods : " + count);
        }
    }
}
