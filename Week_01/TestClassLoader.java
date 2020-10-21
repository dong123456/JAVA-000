package WeekO1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zdd
 */
public class TestClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {

            Class<?> helloClassLoader = new TestClassLoader().findClass("Hello");

            Object object = helloClassLoader.newInstance();

            Method method = helloClassLoader.getMethod("hello");

            method.invoke(object, null);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(this.getClass().getResource("./Hello.xlass").getPath());
        Long length = file.length();
        byte[] byte1 = new byte[length.intValue()];
        try {
            new FileInputStream(file).read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0;  < bytes.length; i++) {
            byte1[i] = (byte) (255 - bytes[i]);
            System.out.print(Integer.toHexString(byte1[i])+"");
        }
        return defineClass(name, byte1,0, length.intValue());
    }
}
