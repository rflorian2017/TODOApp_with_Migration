package com.mac.controller;

import com.mac.model.Task;
import com.mac.model.User;
import com.mac.repository.TaskRepository;
import com.mac.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.event.EventListenerList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

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
    public TextField txtFieldTODO;
    public Button btnInsert;
    public VBox vBoxTasks;
    public TableView tblView;
    public TableColumn colTaskId;
    public TableColumn colTaskDesc;
    public TableColumn colUsername;
    public TabPane tabPane;
    public Tab tabRegister;
    public Tab tabLogin;
    public Tab tabAddTask;
    public Tab tabShowTasks;
    public VBox vBoxTaskList;
    public Tab tabTasks;
    public VBox vBoxTaskListAllocated;


    private UserRepository userRepository;
    private TaskRepository taskRepository;

    private User loggedInUser;

    private boolean isConnectionSuccessful = false;

    public void initialize() {
        try {
            colTaskId.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
            colTaskDesc.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
            colUsername.setCellValueFactory(new PropertyValueFactory<Task, String>("user"));
            persistenceConnection();
            tabPane.getTabs().clear();
            tabPane.getTabs().add(tabLogin);

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
        taskRepository = new TaskRepository(entityManager);
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
        loggedInUser = userRepository.findByUsername(txtFieldUsernameLogin.getText());
        lblInformation.setVisible(true);
        if(loggedInUser != null) {
            tabPane.getTabs().clear();
            tabPane.getTabs().add(tabAddTask);
            tabPane.getTabs().add(tabTasks);

            lblInformation.setText("User login succesfull");
        }
        else {
            lblInformation.setText("User not existing");
        }
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

    public void insertTaskEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            insertTask();
        }
    }

    private void insertTask() {
        Task task = new Task();
        task.setCreatedAt(new Date());
        task.setDescription(txtFieldTODO.getText());

        taskRepository.save(task);

        //TODO show all tasks not only the last added ones
        CheckBox checkBox = new CheckBox();
        checkBox.setText(task.getDescription());

        vBoxTasks.getChildren().add(checkBox);
    }

    public void insertTask(ActionEvent actionEvent) {
        insertTask();
    }

    public void loadTasks(Event event) {
        List<Task> tasks = taskRepository.findAll();
        final ObservableList<Task> dbTasks = FXCollections.observableList(tasks);
        tblView.setItems(dbTasks);
        System.out.println("Loaded tasks");
    }

    public void loadTaskList(Event event) {
        vBoxTaskList.getChildren().clear();
        vBoxTaskListAllocated.getChildren().clear();
        List<Task> tasks = taskRepository.findAll();

        for (final Task task:tasks
             ) {
            CheckBox checkBox = new CheckBox();
            checkBox.setWrapText(false);

            if(task.getUser()!=null) {
                checkBox.setText(task.getId() + ". "
                        + task.getDescription() + " allocated to " + task.getUser().getUsername());
                checkBox.setDisable(true);
                vBoxTaskListAllocated.getChildren().add(checkBox);
            }
            else {
                vBoxTaskList.getChildren().add(checkBox);
                checkBox.setText(task.getId() + ". "
                        + task.getDescription());
                checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    task.setUser(loggedInUser);
                    taskRepository.save(task);
                    task.setInProgress(!newValue);
                    loadTaskList(null);
                });
            }


        }
    }
}
