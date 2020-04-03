import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestTwitter
{


    @Test
    public void testInstation() throws Exception
    {
        Twitter twitter = new Twitter();
        assertNotNull(twitter);
        assertNotNull(Twitter.getMessages());
        assertNotNull(Twitter.getUsers());
        assertNotNull(Twitter.getFollowing());
    }

    @Test
    public void testUserInstation() throws Exception
    {
        Twitter twitter = new Twitter();
        assertNotNull(twitter);
        assertNotNull(Twitter.getUsers());
        final String  user1 = "user1";
        assertTrue(Twitter.AddUser(user1,""));
        assertFalse(Twitter.AddUser(user1,""));
        assertNotNull(Twitter.getUser("","",user1));
        final String  user2 = "user2";
        assertTrue(Twitter.AddUser(user2,""));
        assertFalse(Twitter.AddUser(user2,""));
        assertNotNull(Twitter.getUser("","",user2));
        User user_1 = Twitter.getUser("","",user1);
        assertEquals(user_1.getUserName(),user1);
        User user_2 = Twitter.getUser("","",user2);
        assertEquals(user_2.getUserName(),user2);
    }

    @Test
    public void testPublish() throws Exception
    {
        Twitter twitter = new Twitter();
        final String  user1 = "user1";
        Twitter.AddUser(user1,"");
        String msg_1= "hello1";
        String msg_2 = "hello2";
        String msg_3 =  "hello3";
        User user = Twitter.getUser("","",user1);
        String msg1d = user.publishMessage(msg_1);
        String msg2d = user.publishMessage(msg_2);
        String msg3d = user.publishMessage(msg_3);
        ArrayList<Message> msgs = user.getMessages();
        assertTrue(msgs.size()==3);
        assertEquals(msgs.get(0).getMessage(),msg_1);
        assertEquals(msgs.get(1).getMessage(),msg_2);
        assertEquals(msgs.get(2).getMessage(),msg_3);
        Message msg_for_1d = user.getMessage(msg1d);
        assertNotNull(msg_for_1d);
        assertEquals(msg_for_1d.getMessage(),msg_1);
        Message msg_for_dummy = user.getMessage("non_existent");
        assertNull(msg_for_dummy);
    }

    @Test
    public void testFollow() throws Exception
    {
        Twitter twitter = new Twitter();
        assertNotNull(twitter);
        final String  USER1 = "bob";
        Twitter.AddUser(USER1,"");
        String msg_u11= "Darn! We lost!";
        String msg_u12= "Good game though";

        User bob = Twitter.getUser("","",USER1);
        String msg1d = bob.publishMessage(msg_u11);
        String msg2d = bob.publishMessage(msg_u12);

        final String  USER2 = "alice";
        Twitter.AddUser(USER2,"");
        String msg_u21= "I love the weather today.";
        String msg_u22= "great weekend";
        Thread.sleep(5000);
        User alice = Twitter.getUser("","",USER2);
        String msg_21d = alice.publishMessage(msg_u21);
        String msg_22d = alice.publishMessage(msg_u22);

        bob.followUser(alice.getUserName());
        List<User> users = bob.getfollowers();
        assertEquals(users.size(),1);
        assertEquals(users.get(0).getUserName(),alice.getUserName());
        List<Message> bobtimeline = bob.viewTimeLine();
        assertEquals(bobtimeline.size(),4);
        long alice_msg_count = bobtimeline.stream().filter(msg -> msg.getUser_id().equals(alice.getUserName())).count();
        assertEquals(alice_msg_count,2L);
        bobtimeline.forEach(msg -> System.out.println(msg.getTimeLineMessage()));
        assertTrue(bobtimeline.stream().filter(msg -> msg.getUser_id().equals(alice.getUserName())).map(msg -> msg.getMessage()).collect(Collectors.toList()).contains(msg_u21));

    }

}
