package test;

import leetcode.TLog;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyHandler myHandler = new MyHandler(new Object(){
            public void onSuccess() {
                System.out.println("你好");
            }
        });
        // 参数说明在下文
        ActionListener myCallback = (ActionListener) Proxy.newProxyInstance(
                ProxyTest.class.getClassLoader(),
                new Class[]{ActionListener.class},
                myHandler);
        TLog.e(myCallback.getClass().getSuperclass().getSuperclass());
//        new ProxyTest().test(myCallback);
//        ProxyTest.class.getMethod("test",ActionListener.class).invoke(ProxyTest.class.newInstance(),myCallback);
        new ProxyTest().test(myCallback);
        System.out.println(ActionListener.class.getName());

    }
    public void test(ActionListener actionListener){
        actionListener.onSuccess();
    }
    static class MyHandler implements InvocationHandler {

        Object obj;

        public MyHandler(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return obj.getClass().getMethod(method.getName(),method.getParameterTypes()).invoke(obj,args);
        }
    }
    public interface ActionListener {
        public void onSuccess();
        public void onFailure(int reason);
    }
}
