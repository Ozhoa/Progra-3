import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSwingUI {
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton button1; // Encolar Robots predefinidos
    private JButton button2; // Calcular Valor de Ataque
    private JButton button3; // Copiar Robots por Función
    private JButton button4; // Encolar Robot personalizado

    private ColaRobot colaRobot;

    public MainSwingUI() {
        colaRobot = new ColaRobot();

        // R1: Encolar Robots predefinidos
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colaRobot.encolarRobots();
                textArea1.setText(colaRobot.mostrarCola());
            }
        });

        // R2: Calcular Valor de Ataque
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(colaRobot.calcularValorAtaque());
            }
        });

        // R3: Copiar Robots por Función
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] funciones = {"Volar", "Liderar"};
                String funcionSeleccionada = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione una función para copiar robots:",
                        "Copiar Robots por Función",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        funciones,
                        funciones[0]
                );

                if (funcionSeleccionada != null) {
                    String resultado = colaRobot.copiarPorFuncion(funcionSeleccionada);
                    textArea1.setText(resultado);
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] opciones = {"Optimus", "Bumblebee", "Megatron", "Starscream"};
                String seleccion = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione un robot para agregar a la cola:",
                        "Agregar Robot Existente",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (seleccion != null) {
                    Robot robotSeleccionado = null;
                    switch (seleccion) {
                        case "Optimus":
                            robotSeleccionado = new Robot(1, "Optimus", "Autobot", 200, "Liderar");
                            break;
                        case "Bumblebee":
                            robotSeleccionado = new Robot(2, "Bumblebee", "Autobot", 150, "Espiar");
                            break;
                        case "Megatron":
                            robotSeleccionado = new Robot(3, "Megatron", "Decepticon", 300, "Destruir");
                            break;
                        case "Starscream":
                            robotSeleccionado = new Robot(4, "Starscream", "Decepticon", 180, "Volar");
                            break;
                    }

                    if (robotSeleccionado != null) {
                        colaRobot.encolarRobotPersonalizado(robotSeleccionado);
                        textArea1.setText(colaRobot.mostrarCola());
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Robots");
        frame.setContentPane(new MainSwingUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}