import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class DataFaker {

    @Test
    void generateFakeData(){

        Faker faker= new Faker();

        for (int i=0; i<3; i++) {

            String firstName= faker.name().firstName();
            String lastName= faker.name().lastName();
            String userName= faker.name().username();
            String email= faker.internet().emailAddress();
            String password= faker.internet().password();

            System.out.println("firstName: " + firstName);
            System.out.println("lastName: " + lastName);
            System.out.println("userName: " + userName);
            System.out.println("email: " + email);
            System.out.println("password: " + password);
            System.out.println();

        }

    }

}
