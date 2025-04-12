import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Ventana {
    private JPanel panel1;
    private JTextField txtTexto;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JPanel principal;
    private JTextArea txtCodigo;
    private JButton btnComprobar;
    private JLabel lblCodigo;
    private JTextArea txtImpresion;

    public Ventana() {
        btnComprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txtImpresion.setText("");
                    Pila pilas = new Pila();
                    String codigo = txtCodigo.getText();
                    for (int i = 0; i <= codigo.length()-1; i++) {
                        char c = codigo.charAt(i);
                        if (c == '(' || c == '{' || c == '[') {
                            pilas.insertar(String.valueOf(c));
                            JOptionPane.showMessageDialog(null, "Ingresando: " + c);
                            txtImpresion.append("Ingresando: " + c + "\n");
                            txtImpresion.append("Estado de la pila:\n" + pilas.toString());
                        } else if (c == ')' || c == '}' || c == ']') {
                            if (pilas.esVacia()) {
                                JOptionPane.showMessageDialog(null, "Error: Pila vacia - No hay simbolo de apertura para " + c);
                                return;
                            }

                            char salida = pilas.extraer().charAt(0);
                            JOptionPane.showMessageDialog(null, "Extrayendo: " + salida);
                            txtImpresion.append("Extrayendo: " + salida + "\n");

                            if ((c == ')' && salida != '(') ||
                                    (c == '}' && salida != '{') ||
                                    (c == ']' && salida != '[')) {
                                JOptionPane.showMessageDialog(null, "Codigo no balanceado");
                                return;
                            }
                            txtImpresion.append("Estado de la pila:\n" + pilas.toString());
                        }
                    }
                    if (pilas.esVacia())
                        JOptionPane.showMessageDialog(null, "Codigo balanceado");
                    else
                        JOptionPane.showMessageDialog(null, "Codigo no balanceado");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
