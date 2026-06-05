package Vista;

import Controlador.SessionController;
import Controlador.RuletaController;
import Modelo.Persistencia.IRepositorioResultados;
import Modelo.Persistencia.RepositorioArchivo;
import Modelo.Persistencia.RepositorioEnMemoria;
import Modelo.Ruleta;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {

    private final SessionController sessionController;

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private JButton btnIrRegistro;

    public VentanaLogin(SessionController sessionController) {
        this.sessionController = sessionController;
        inicializar();
    }

    private void inicializar() {
        setTitle("Login");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Clave:"));
        txtClave = new JPasswordField();
        add(txtClave);

        btnIngresar = new JButton("Ingresar");
        btnIrRegistro = new JButton("Registrarse");

        add(btnIngresar);
        add(btnIrRegistro);

        btnIngresar.addActionListener(e -> intentarLogin());
        btnIrRegistro.addActionListener(e -> abrirRegistro());
    }

    private void intentarLogin() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtClave.getPassword());

        boolean ok = sessionController.iniciarSesion(usuario, clave);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Bienvenido " + sessionController.getNombreUsuario());

            // Repositorio persistente en archivo. Para probar memoria, cambia la línea por:
            // IRepositorioResultados repositorio = new RepositorioEnMemoria();
            IRepositorioResultados repositorio = new RepositorioArchivo("historial_ruleta.csv");

            Ruleta ruleta = new Ruleta(1000, repositorio);
            RuletaController ruletaController = new RuletaController(ruleta, sessionController);

            new VentanaMenu(sessionController, ruletaController).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }

    private void abrirRegistro() {
        new VentanaRegistro(sessionController).setVisible(true);
        dispose();
    }
}
