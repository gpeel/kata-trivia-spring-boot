package transfo.notfinished;

import javax.annotation.PostConstruct;
import java.io.OutputStream;
import java.io.PrintStream;

//@Component
public class Interceptor extends PrintStream {

    StringBuffer stringBuffer;

    @PostConstruct
    public void init() {
        PrintStream origOut = System.out;
        PrintStream interceptor = new Interceptor(origOut);
        System.setOut(interceptor);// just add the interceptor
        stringBuffer = new StringBuffer();
    }

    public Interceptor(OutputStream out) {
        super(out, true);
    }

    public void reset() {
        stringBuffer = new StringBuffer();
    }

    @Override
    public void print(String s) {
        super.print(s);
        //do what ever you like
        this.stringBuffer.append(s);
    }

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }
}