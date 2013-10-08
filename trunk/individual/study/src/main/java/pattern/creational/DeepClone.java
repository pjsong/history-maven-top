package pattern.creational;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Ҫʵ�����¡����ʵ��Serializable�ӿ�
class ToBeDeepCloned implements Serializable
{
    private int a;
    private String b;
    private int[] c;

    public int getA()
    {
        return a;
    }

    public void setA(int a)
    {
        this.a = a;
    }

    public String getB()
    {
        return b;
    }

    public void setB(String b)
    {
        this.b = b;
    }

    public int[] getC()
    {
        return c;
    }

    public void setC(int[] c)
    {
        this.c = c;
    }

}
public class DeepClone {

	public static void main(String[] args) throws CloneNotSupportedException {
		DeepClone t = new DeepClone();
		ToBeDeepCloned dc1 = new ToBeDeepCloned();
		// ��dc1��ֵ
		dc1.setA(100);
		dc1.setB("clone1");
		dc1.setC(new int[] { 1000 });

		System.out.println("��¡ǰ: dc1.a=" + dc1.getA());
		System.out.println("��¡ǰ: dc1.b=" + dc1.getB());
		System.out.println("��¡ǰ: dc1.c[0]=" + dc1.getC()[0]);
		System.out.println("-----------");

		ToBeDeepCloned dc2 = (ToBeDeepCloned) t.deepClone(dc1);
		// ��c2�����޸�
		dc2.setA(50);
		dc2.setB("clone2");
		int[] a = dc2.getC();
		a[0] = 500;
		dc2.setC(a);

		System.out.println("��¡ǰ: dc1.a=" + dc1.getA());
		System.out.println("��¡ǰ: dc1.b=" + dc1.getB());
		System.out.println("��¡ǰ: dc1.c[0]=" + dc1.getC()[0]);
		System.out.println("-----------");

		System.out.println("��¡��: dc2.a=" + dc2.getA());
		System.out.println("��¡��: dc2.b=" + dc2.getB());
		System.out.println("��¡��: dc2.c[0]=" + dc2.getC()[0]);

	}

	// �����л��뷴���л�ʵ�����¡
	public Object deepClone(Object src) {
		Object o = null;
		try {
			if (src != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(src);
				oos.close();

				ByteArrayInputStream bais = new ByteArrayInputStream(baos
						.toByteArray());
				ObjectInputStream ois = new ObjectInputStream(bais);

				o = ois.readObject();
				ois.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}

}