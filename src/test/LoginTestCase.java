package test;

import main.LoginApp;
import org.junit.Test;

import static org.junit.Assert.*;


public class LoginTestCase {
        private LoginApp loginApp;

        @Test
        public void testValidLogin() {
            loginApp = new LoginApp();
            String userName = loginApp.authenticateUser("johndoe@example.com","password123");
            assertEquals("John Doe",userName );

            userName = loginApp.authenticateUser("janesmith@example.com","password456");
            assertEquals("Jane Smith",userName );
        }

        @Test
        public void testInvalidEmail() {
            loginApp = new LoginApp();
            String userName = loginApp.authenticateUser("invalid@example.com", "password123");
            assertNull(userName);

            userName = loginApp.authenticateUser("invalid@example.com","password456");
            assertNull(userName);
        }

        @Test
        public void testInvalidPassword() {
            loginApp = new LoginApp();
            String userName = loginApp.authenticateUser("johndoe@example.com","invalidPassword");
            assertNull(userName);

            userName = loginApp.authenticateUser("janesmith@example.com","invalid");
            assertNull(userName);
        }

        @Test
        public void testEmptyFields() {
            loginApp = new LoginApp();
            String userName = loginApp.authenticateUser("", "password123");
            assertNull(userName);

            userName= loginApp.authenticateUser("janesmith@example.com", "");
            assertNull(userName);
        }

        @Test
        public void testSQLAttack() {
            loginApp = new LoginApp();
            String userName = loginApp.authenticateUser("johndoe@example.com","invalid\"' OR '1'='1\"");
            assertNull(userName);
        }
}
