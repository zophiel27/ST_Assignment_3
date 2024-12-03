import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTestCases {
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
            assertEquals(null,userName);

            userName = loginApp.authenticateUser("invalid@example.com","password456");
            assertEquals(null,userName);
        }

        @Test
        public void testInvalidPassword() {
            loginApp = new LoginApp();
            String userName = loginApp.authenticateUser("johndoe@example.com","invalidPassword");
            assertEquals(null,userName);

            userName = loginApp.authenticateUser("janesmith@example.com","invalid");
            assertEquals(null,userName);
        }

        @Test
        public void testEmptyFields() {
            loginApp = new LoginApp();
            String userName = loginApp.authenticateUser("", "password123");
            assertEquals(null,userName);

            userName= loginApp.authenticateUser("janesmith@example.com", "");
            assertEquals(null,userName);
        }

        @Test
        public void testSQLAttack() {
            loginApp = new LoginApp();
            String userName = loginApp.authenticateUser("johndoe@example.com","invalid\"' OR '1'='1\"");
            assertEquals(null,userName);
        }
}
