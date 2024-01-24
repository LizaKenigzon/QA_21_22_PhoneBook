package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"test111@gmail.com", "Aa12345$"});
        list.add(new Object[]{"test111@gmail.com", "Aa12345$"});
        list.add(new Object[]{"sonya@gmail.com", "Ss12345$"});


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        //read from file ---> add to list
        BufferedReader reader = new BufferedReader(new FileReader
                (new File("src/test/resources/data.csv")));
        String line = reader.readLine();/// margo@gmail.com,Mmar123456$
        while (line != null) {
            String[] all =  line.split(",");//["margo@gmail.com"] ["Mmar123456$"]
            list.add(new Object[]{
                    new User().setEmail(all[0]).setPassword(all[1])});
            line = reader.readLine(); ///sonya@gmail.com,Ss12345$

        }
        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> loginModels() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().setEmail("test111@gmail.com").setPassword("Aa12345$")});
        list.add(new Object[]{new User().setEmail("sonya@gmail.com").setPassword("Ss12345$")});

        return list.iterator();
    }

}