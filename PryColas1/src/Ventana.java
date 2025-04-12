import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel principal;
    private JComboBox cboModelo;
    private JTextField txtAnio;
    private JButton btnAgregar;
    private JButton btnDesencolar;
    private JTextArea txtListarAutos;
    private JLabel lblMensaje;
    private ColaAutos listaAutos=new ColaAutos();

    public Ventana() {
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtAnio.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El año no puede tener un espacio vacío");
                        return;
                    }
                    int anio = Integer.parseInt(txtAnio.getText());
                    if (anio > 2025) {
                        JOptionPane.showMessageDialog(null, "No se permiten años mayores a 2025");
                        return;
                    }
                    if (anio <= 0) {
                        JOptionPane.showMessageDialog(null, "No se permiten años negativos o cero");
                        return;
                    }
                    String modelo = cboModelo.getSelectedItem().toString();
                    Auto auto = new Auto(modelo, anio);
                    listaAutos.encolar(auto);
                    txtListarAutos.setText(listaAutos.listarAutos());
                    lblMensaje.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El año debe ser un numero valido");
                }
            }
        });


        btnDesencolar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Auto autox = listaAutos.desencolar();
                    txtListarAutos.setText(listaAutos.listarAutos());
                    lblMensaje.setText("Auto atendido " + autox.toString() + " debe pagar $" + autox.calcularPrecio());
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
        //frame.pack();
        frame.setSize(600,400);
        frame.setVisible(true);
    }
}
