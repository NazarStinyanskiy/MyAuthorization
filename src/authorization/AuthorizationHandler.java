package authorization;

import authorization.enums.LogStatus;
import authorization.enums.RegStatus;

import java.io.*;

/**
 * Unacceptable symbols for login and password: " "    "="
 */



public class AuthorizationHandler {     // обработчик

    private final String path = "/home/nazar/IdeaProjects/authorization/users-info/";

    public RegStatus reg(String login, String password) {
        if(isUserExist(login)){return RegStatus.USER_EXIST;}

        if(!isPassLogCorrect(login)){return RegStatus.BAD_LOGIN_CHARACTER;}
        if(!isPassLogCorrect(password)){return RegStatus.BAD_PASSWORD_CHARACTER;}
        if(login.equals("")){return  RegStatus.BAD_LOGIN_CHARACTER;}
        if(password.length() < 4){return RegStatus.BAD_PASSWORD_LENGTH;}

        try(FileWriter writer = new FileWriter(path + "usr-inf.txt", true); FileReader reader = new FileReader(path + "usr-count.txt")) {
            StringBuilder count = new StringBuilder();
            int c = reader.read();

            while (c != -1){
                if(Character.isDigit((char) c)) {
                    count.append((char) c);
                }

                c = reader.read();
            }

            writer.write(((Integer.parseInt(count.toString())) + 1) + " " + login + "=" + password + "\n");

            try(PrintWriter countWriter = new PrintWriter(path + "usr-count.txt", "UTF-8")){
                int i = (Integer.parseInt(count.toString()) + 1);
                countWriter.write(Integer.toString(i));
            }

        }catch (IOException e){}

        return RegStatus.SUCCESS;
    }

    public LogStatus log(String login, String password) {
        if(!isUserExist(login)) {return LogStatus.UN_CORRECT_LOGIN;}
        if(!checkPassword(login, password)){return  LogStatus.WRONG_PASSWORD;}

        return LogStatus.SUCCESS;
    }

    private boolean isUserExist(String login) {

        StringBuilder finder = new StringBuilder();

        try (FileReader reader = new FileReader(path + "usr-inf.txt")) {
            int c = reader.read();
            while (c != -1) {

                while(Character.isDigit((char) c) || (char) c == ' ' || (char) c == '\n'){
                    c = reader.read();
                }

                while (isCharCorrect((char) c)) {
                    finder.append((char) c);
                    c = reader.read();
                }

                if(finder.toString().equals(login)){
                    return true;
                }else {
                    finder = new StringBuilder();
                }

                while((char) c != '\n'){
                    c = reader.read();
                }
                c = reader.read();
            }
        } catch (IOException e) { }

        return false;
    }

    private boolean checkPassword(String login, String password){
        String usr_info = login + "=" + password;
        StringBuilder finder = new StringBuilder();

        try(FileReader reader = new FileReader(path + "usr-inf.txt")){
            int c = reader.read();

            while(c != -1){
                while(Character.isDigit((char) c) || (char) c == ' '){
                    c = reader.read();
                }

                while ((char) c != '\n') {
                    finder.append((char) c);
                    c = reader.read();
                }

                if(finder.toString().equals(usr_info)){
                    return true;
                }else {
                    finder = new StringBuilder();
                }

                c = reader.read();
            }
        }catch(IOException e){ }
        return false;

    }

    private boolean isPassLogCorrect(String arg) {
        for(int i = 0; i < arg.length(); i++){
            if(!isCharCorrect(arg.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private boolean isCharCorrect(char c){
        if (c == ' ' || c ==  '=') {
            return false;
        }

        return true;
    }
}