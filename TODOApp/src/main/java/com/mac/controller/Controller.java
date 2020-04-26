package com.mac.controller;

import com.mac.model.User;
import com.mac.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Controller {


    public Label lblInformation;
    public PasswordField pwdFieldConfirmRegister;
    public PasswordField pwdFieldRegister;
    public TextField txtFieldUsernameRegister;
    public Button btnRegister;
    public TextField txtFieldUsernameLogin;
    public PasswordField pwdFieldLogin;
    public Button btnLogin;
    public Button btnShowPwdRegister;
    public TextField txtFieldPwdRegister;
    public TextField txtFieldPwdConfirmRegister;
    public Button btnShowPwdConfirmRegister;
    public TextField txtFieldPwdLogin;
    public Label lblUsernameRegister;
    public Label lblPasswordRegister;
    public Label lblConfirmPasswordRegister;
    public Button btnShowLogin;


    private UserRepository userRepository;
    private boolean isConnectionSuccessful = false;

    public void initialize() {
        try {
            persistenceConnection();
        } catch (Exception e) {
            System.out.println("Connection is not allowed");
            isConnectionSuccessful = false;
        }

    }

    private void persistenceConnection() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("TODOFx");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userRepository = new UserRepository(entityManager);
    }

    public void registerUser(ActionEvent actionEvent) {
        lblUsernameFieldRegisterWarning();
        lblPasswordWarning();
        lblConfirmPasswordWarning();
        isAlreadyIn();
    }

    private void lblUsernameFieldRegisterWarning() {
        if (txtFieldUsernameRegister.getText().length() < 1) {
            lblUsernameRegister.setTextFill(Color.RED);
            lblInformation.setVisible(true);
            lblInformation.setTextFill(Color.RED);
            lblInformation.setText("Please fill Username");
        } else {
            lblUsernameRegister.setTextFill(Color.BLACK);
            lblInformation.setVisible(false);
        }
    }

    private void lblPasswordWarning() {
        if (txtFieldPwdRegister.getText().length() < 1) {
            lblPasswordRegister.setTextFill(Color.RED);
            lblInformation.setVisible(true);
            lblInformation.setTextFill(Color.RED);
            lblInformation.setText("Please fill Password field");
        } else {
            lblPasswordRegister.setTextFill(Color.BLACK);
            lblInformation.setVisible(false);
        }
    }

    private void lblConfirmPasswordWarning() {
        if (txtFieldPwdConfirmRegister.getText().length() < 1) {
            lblConfirmPasswordRegister.setTextFill(Color.RED);
            lblInformation.setVisible(true);
            lblInformation.setTextFill(Color.RED);
            lblInformation.setText("Please fill Confirm password field");
        } else {
            lblConfirmPasswordRegister.setTextFill(Color.BLACK);
            lblInformation.setVisible(false);
        }
    }

    private void isAlreadyIn() {
        User user = userRepository.findByUsername(txtFieldUsernameRegister.getText());
        if (pwdFieldRegister.getText().equals(pwdFieldConfirmRegister.getText()) && user == null) {
            user = new User();
            user.setUsername(txtFieldUsernameRegister.getText());
            user.setPassword(pwdFieldRegister.getText());
            userRepository.save(user);
        } else {
            lblInformation.setText("Is already in");
        }
    }


    public void loginUser(ActionEvent actionEvent) {

    }

    private void showPassword(TextField showPassword, PasswordField pwdField) {
        showPassword.setText(pwdField.getText());
        showPassword.setEditable(false);
        showPassword.setVisible(true);
        pwdField.setVisible(false);
    }

    public void showPassword(ActionEvent actionEvent) {
        if (!txtFieldPwdRegister.isVisible()) {
            btnShowPwdRegister.setText("Hide");
            showPassword(txtFieldPwdRegister, pwdFieldRegister);
        } else {
            btnShowPwdRegister.setText("Show");
            txtFieldPwdRegister.setVisible(false);
            pwdFieldRegister.setVisible(true);
        }
    }

    public void showConfirmPassword(ActionEvent actionEvent) {
        if (!txtFieldPwdConfirmRegister.isVisible()) {
            btnShowPwdConfirmRegister.setText("Hide");
            showPassword(txtFieldPwdConfirmRegister, pwdFieldConfirmRegister);

        } else {
            btnShowPwdConfirmRegister.setText("Show");
            txtFieldPwdConfirmRegister.setVisible(false);
            pwdFieldConfirmRegister.setVisible(true);
        }
    }

    public void showPasswordLogin(ActionEvent actionEvent) {
        if (!txtFieldPwdLogin.isVisible()) {
            btnShowLogin.setText("Hide");
            showPassword(txtFieldPwdLogin, pwdFieldLogin);

        } else {
            btnShowLogin.setText("Show");
            txtFieldPwdLogin.setVisible(false);
            pwdFieldLogin.setVisible(true);
        }
    }
}
