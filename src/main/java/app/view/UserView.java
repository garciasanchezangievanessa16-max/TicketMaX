package app.view;

import app.domain.User;
import app.domain.enums.SelectPreferencesEnum;
import app.service.helpers.SetUserState;
import app.service.inputports.UserService;
import app.service.validators.DataTypeValidator;

import java.util.List;

public class UserView {

    private final UserService userService;

    public UserView(UserService userService){
        this.userService = userService;
    }

    public void createUser() {

        int id = DataTypeValidator.validateInt("INgrese el id del usuario: ");
        String name = DataTypeValidator.validateString("Ingrese el nombre del usuario: ");
        String lastName = DataTypeValidator.validateString("Ingrese el apellido del usuario: ");
        String email = DataTypeValidator.validateString("Ingrese el correo del usuario: ");
        String phone = DataTypeValidator.validateString("Ingrese el telefono del usuario: ");
        System.out.println("Ingrese la contraseña del usuario: ");
        String password = DataTypeValidator.validateString("Ingrese la contraseña del usuario: ");
        System.out.println("Ingrese el estado del usuario: ");
        String state = SetUserState.getUserState();
        System.out.println();
        String city = DataTypeValidator.validateString("Ingrese la ciudad del usuario: ");
        System.out.println("Ingrese las preferencias del usuario: ");
        String preferences = setUserPreferences();

        User createdUser = userService.create(id, name , lastName , email , phone , password , state, city, preferences);
        System.out.println("Usuario creado: " + createdUser.getId());
    }

    public User selectUserById(int id) {
        User user = userService.selectUserById(id);
        if (user == null) {
            System.out.println("No se encontró el usuario con el id " + id);
            return null;
        }

        System.out.println("Usuario encontrado: " + user.getId() + " " + user.getName() + " " + user.getLastName()
                + " " + user.getEmail() + " " + user.getPhone() + " " + user.getCity() + " " + user.getPreferences());
        return user;
    }

    public User selectById(int id) {
        return selectUserById(id);
    }

    public void selectUsers(){
        List<User> users = userService.selectUsers();
        if (users.isEmpty()) {
            System.out.println("No hay usuarios registrados");
            return;
        }

        System.out.println("Listado de usuarios:");
        for (User user : users) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getLastName()
                    + " " + user.getEmail() + " " + user.getPhone() + " " + user.isState()
                    + " " + user.getCity() + " " + user.getPreferences());
        }
    }

    public void updateUser() {
        int id = DataTypeValidator.validateInt("Ingrese el id del usuario a actualizar: ");
        User user = userService.selectUserById(id);

        if (user == null) {
            System.out.println("No se encontró el usuario con el id " + id);
            return;
        }

        String name = DataTypeValidator.validateString("Ingrese el nuevo nombre del usuario: ");
        String lastName = DataTypeValidator.validateString("Ingrese el nuevo apellido del usuario: ");
        String email = DataTypeValidator.validateString("Ingrese el nuevo correo del usuario: ");
        String phone = DataTypeValidator.validateString("Ingrese el nuevo telefono del usuario: ");
        String password = DataTypeValidator.validateString("Ingrese la nueva contraseña del usuario: ");
        System.out.println("Ingrese el nuevo estado del usuario: ");
        String state = SetUserState.getUserState();
        String city = DataTypeValidator.validateString("Ingrese la nueva ciudad del usuario: ");
        String preferences = setUserPreferences();

        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setState(state);
        user.setCity(city);
        user.setPreferences(preferences);

        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            System.out.println("Usuario actualizado correctamente");
        }
    }

    public void deleteUser(int id) {
        User user = userService.selectUserById(id);
        if (user == null) {
            System.out.println("No se encontró el usuario con el id " + id);
            return;
        }

        userService.deleteUser(id);
        System.out.println("Usuario eliminado correctamente");
    }

    public void countUsers() {
        int total = userService.countUsers();
        System.out.println("Total de usuarios: " + total);
    }

    // métodos Helper

    public String setUserPreferences(){

        int option = DataTypeValidator.validateInt("Seleccione 1. VIP 2. General 3. Preferencial");
        String preferences = "";

        switch (option){
            case 1:
                preferences = SelectPreferencesEnum.VIP.getPreference();
                break;
            case 2:
                preferences = SelectPreferencesEnum.GENERAL.getPreference();
                break;
            case 3:
                preferences = SelectPreferencesEnum.PREFERENCIAL.getPreference();
                break;
            default:
                System.out.println("Opción no valida");
        }
        return preferences;
    }

}
