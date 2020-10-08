package gauthier;

import org.fest.reflect.core.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

// Spec is
// factorize :
//private final ByteArrayOutputStream outContent=new ByteArrayOutputStream();
//private final ByteArrayOutputStream errContent=new ByteArrayOutputStream();
//private final PrintStream originalOut=System.out;
//private final PrintStream originalErr=System.err;
//
//@BeforeEach
//public void setUpStreams(){
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//        }
//
//@AfterEach
//public void restoreStreams(){
//        System.setOut(originalOut);
//        System.setErr(originalErr);
//        }

/**
 * Taget Test class will only have to declare
 * <pre>
 *     @OutContent
 *     private ByteArrayOutputStream outContent;
 *     @ErrContent
 *     private ByteArrayOutputStream errContent;
 *  </pre>
 * and this TestExecutionListener will do the Job!
 * So you can use the outContent in your test : ie extract the test-generated output to a previously recorded output
 * with something like
 * assertThat(outContent.toString()).isEqualTo(PREVIOUSLY_RECORDED_RESPONSE);
 */
public class SystemBufferTestExecutionListener extends AbstractTestExecutionListener implements Ordered {
    private static final Logger logger = LoggerFactory.getLogger(SystemBufferTestExecutionListener.class);
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    public void beforeTestClass(TestContext testContext) throws Exception {
        logger.info("beforeTestClass : {}", testContext.getTestClass());
    }


    public void prepareTestInstance(TestContext testContext) throws Exception {
        logger.info("prepareTestInstance : {}", testContext.getTestClass());
        Object testInstance = testContext.getTestInstance();
        // get a reference to a potential @OutContent

        for (Field field : testInstance.getClass().getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotationsByType(OutContent.class);
            if (annotations.length > 0) {
                System.out.println(field.getName());
                Reflection.field(field.getName())
                        .ofType(ByteArrayOutputStream.class)
                        .in(testInstance)
                        .set(new ByteArrayOutputStream());
                outContent = Reflection.field(field.getName())
                        .ofType(ByteArrayOutputStream.class)
                        .in(testInstance)
                        .get();
            }
            annotations = field.getDeclaredAnnotationsByType(ErrContent.class);
            if (annotations.length > 0) {
                System.out.println(field.getName());
                Reflection.field(field.getName())
                        .ofType(ByteArrayOutputStream.class)
                        .in(testInstance)
                        .set(new ByteArrayOutputStream());
                errContent = Reflection.field(field.getName())
                        .ofType(ByteArrayOutputStream.class)
                        .in(testInstance)
                        .get();
            }
        }


    }

    public void beforeTestMethod(TestContext testContext) throws Exception {
        logger.info("beforeTestMethod : {}", testContext.getTestMethod());
        if (outContent != null) {
            System.setOut(new PrintStream(outContent));
        }
        if (errContent != null) {
            System.setErr(new PrintStream(errContent));
        }
    }


    public void afterTestMethod(TestContext testContext) throws Exception {
        logger.info("afterTestMethod : {}", testContext.getTestMethod());
        if (outContent != null) {
            System.setOut(originalOut);
        }
        if (errContent != null) {
            System.setErr(originalErr);
        }
    }

    public void afterTestClass(TestContext testContext) throws Exception {
        logger.info("afterTestClass : {}", testContext.getTestClass());
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}