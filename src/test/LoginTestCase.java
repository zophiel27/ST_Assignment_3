package test;

import main.LoginApp;
import org.junit.Test;

import static org.junit.Assert.*;


public class LoginTestCase {

        @Test
        public void testValidLogin() {
            String userName = LoginApp.authenticateUser("johndoe@example.com","password123");
            assertEquals("John Doe",userName );

            userName = LoginApp.authenticateUser("janesmith@example.com","password456");
            assertEquals("Jane Smith",userName );
        }

        @Test
        public void testInvalidEmail() {
            String userName = LoginApp.authenticateUser("invalid@example.com", "password123");
            assertNull(userName);

            userName = LoginApp.authenticateUser("invalid@example.com","password456");
            assertNull(userName);
        }

        @Test
        public void testInvalidPassword() {
            String userName = LoginApp.authenticateUser("johndoe@example.com","invalidPassword");
            assertNull(userName);

            userName = LoginApp.authenticateUser("janesmith@example.com","invalid");
            assertNull(userName);
        }

        @Test
        public void testEmptyFields() {
            String userName = LoginApp.authenticateUser("", "password123");
            assertNull(userName);

            userName= LoginApp.authenticateUser("janesmith@example.com", "");
            assertNull(userName);
        }

        @Test
        public void testSQLAttack() {
            String userName = LoginApp.authenticateUser("johndoe@example.com","invalid\"' OR '1'='1\"");
            assertNull(userName);
        }
}
