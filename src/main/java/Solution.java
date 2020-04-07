import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    public static void main(String args[]) {
        Twitter twitter = new Twitter();


        String user_input;
        printOptions();

        Scanner sn = new Scanner(System.in);
        String current_user = null;

        while (true) {
            printStr("Enter your choice:");

            user_input = sn.next();

            //Check the user input
            switch (user_input) {
                case "1":
                    printStr("Enter userName:");
                    String name = sn.next();
                    if (printMsg(name, "Please enter a valid input:")) {
                        if (Twitter.AddUser(name, "")) {
                            printStr("Successfully added user:" + name);
                        } else {
                            printStr("User already exists:" + name);
                        }
                    }
                    break;
                case "2":
                    ListUsers();

                    break;

                case "3":
                    printStr("Choose User(Enter number) :");
                    ListUsers();
                    String userStr = sn.next();
                    if (printMsg(userStr, "Invalid input:")) {
                        printStr("User selected:" + userStr);
                        current_user = userStr;
                    }
                    break;
                case "4":
                    if (printMsg(current_user, "No user selected , please select a user:")) {
                        printStr("Publishing as user :" + current_user);
                        User user = Twitter.getUser("", "", current_user);
                        printStr("Please enter the message :");
                        String msg = sn.next();
                        if (printMsg(msg, "Invalid input")) {
                            if (user.publishMessage(msg) != null)
                                printStr("Message published");
                            else
                                printStr("Message publish failed");

                        }
                    }
                    break;

                case "5":
                    if (printMsg(current_user, "No user selected , please select a user:")) {
                        printStr("Viewing Timeline for user:" + current_user);
                        User user = Twitter.getUser("", "", current_user);
                        user.viewTimeLine().forEach(msg -> printStr(msg.getTimeLineMessage()));
                    }
                    break;
                case "6":
                    if (printMsg(current_user, "No user selected , please select a user:")) {
                        printStr("Current user:" + current_user);
                        Set<String> userSet = Twitter.getUsers().keySet();
                        ListUsers();
                        printStr("Please enter a user:");
                        String user2follow = sn.next();
                        if (printMsg(user2follow, "Invalid input")) {
                            if (user2follow.equals(current_user)) {
                                printStr("User cannot follow himself");
                            } else {
                                if (userSet.contains(user2follow)) {
                                    User user = Twitter.getUser("", "", current_user);
                                    if (user.followUser(user2follow)) {
                                        printStr("FollowUser succeeded");
                                    } else {
                                        printStr("User alreading in followers list");
                                    }
                                    printStr("Current Followers  :");
                                    for (User follower : user.getfollowers()) {
                                        printStr(follower.getUserName());
                                    }
                                } else {
                                    printStr("No such user, please try again");
                                }

                            }
                        }
                    }
                    break;
                case "0":

                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");

            }
            printOptions();
        }
    }

    public static boolean printMsg(String incoming, String errMsg) {
        if (incoming == null || incoming.equals("")) {
            System.out.println(errMsg);
            return false;
        }
        return true;
    }

    public static void ListUsers() {
        int count = 0;
        for (String us : Twitter.getUsers().keySet()) {
            count++;
            System.out.println("User#(" + count + ") :" + us);
        }
    }

    public static void printStr(String str) {
        System.out.println(str);
    }

    public static void printOptions() {
        System.out.println("#############");
        System.out.println(" Press 1 => Add  User");
        System.out.println(" Press 2 => List Users");
        System.out.println(" Press 3 =>  Change User");
        System.out.println(" Press 4 =>  Publish");
        System.out.println(" Press 5 =>  ViewTimeLine");
        System.out.println(" Press 6 =>  FollowUser");
        System.out.println("Press 0 to exit");
        System.out.println("#############");
    }
}
