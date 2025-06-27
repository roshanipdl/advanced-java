import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class ListStudentProperties {
    public static void main(String[] args) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        System.out.println("Properties of Student class:");
        for (PropertyDescriptor pd : propertyDescriptors) {
            if (!pd.getName().equals("class")) {
                System.out.println("Property: " + pd.getName() +
                                   ", Type: " + pd.getPropertyType().getSimpleName());
            }
        }
    }
} 