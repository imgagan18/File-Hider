package views;

import DAO.UserDAO;
import Service.GenerateOTP;
import Service.SendOTPService;
import Service.UserService;
import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void WelcomeScreen(){
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the application");
        System.out.println("press 1 to Login");
        System.out.println("press 2 to Signup ");
        System.out.println("press 0 to exit the app ");
        int choice=0;
        try {
            choice=Integer.parseInt(br.readLine());
        }catch (IOException ex){
            ex.printStackTrace();
        }
        switch (choice){
            case 1 -> login();
            case 2 -> signUp();
            case 3 -> System.exit(0);


        }
    }

    private void login() {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter Email ID");
        String email=sc.nextLine();
        try{
            if(UserDAO.isExist(email)){
                String genOTP=GenerateOTP.GetOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("enter the otp sent");
                String otp=sc.nextLine();
                if(otp.equals(genOTP)){
                    new UserView(email).home();

                }else{
                    System.out.println("wrong OTP");
                }
            }else {
                System.out.println("User Not Found");
            }
        }catch (SQLException ex){
             ex.printStackTrace();
        }
    }

    private void signUp() {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the name ");
        String name=sc.nextLine();
        System.out.println("enter the email ID");
        String email=sc.nextLine();
        String genOTP=GenerateOTP.GetOTP();
        SendOTPService.sendOTP(email,genOTP);
        System.out.println("enter the otp sent");
        String otp=sc.nextLine();
        if(otp.equals(genOTP)){
            User user=new User(name,email);
            int response= UserService.saveUser(user);
            switch (response){
                case 0-> System.out.println("User Registered");
                case 1-> System.out.println("user already Exists");
            }
        }else{
            System.out.println("wrong OTP");
        }

    }
}
