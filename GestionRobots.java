
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GestionRobots extends JFrame {
    private JTextField txtId, txtNombre, txtPuntaje;
    private JComboBox<Integer> comboEnergia;
    private JComboBox<String> comboCategoria, comboBuscarCategoria;
    private JTextArea textArea;
    private java.util.List<Robot> listaRobots;

    public GestionRobots() {
        setTitle("Gestión de Robots");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listaRobots = new ArrayList<>();
        inicializarDatos();

        JPanel panel = new JPanel(new GridLayout(8, 2));
        txtId = new JTextField();
        txtNombre = new JTextField();
        txtPuntaje = new JTextField();
        comboEnergia = new JComboBox<>();
        for (int i = 10; i <= 100; i += 10) comboEnergia.addItem(i);

        comboCategoria = new JComboBox<>(new String[]{"DRON", "TERRESTRE", "SUBMARINO", "AÉREO"});
        comboBuscarCategoria = new JComboBox<>(new String[]{"DRON", "TERRESTRE", "SUBMARINO", "AÉREO"});
        JButton btnAgregar = new JButton("Agregar/Reemplazar Robot");
        JButton btnBuscar = new JButton("Buscar por Categoría");
        JButton btnEditar = new JButton("Editar por ID");
        JButton btnOrdenar = new JButton("Ordenar por ID");

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Energía:"));
        panel.add(comboEnergia);
        panel.add(new JLabel("Puntaje:"));
        panel.add(txtPuntaje);
        panel.add(new JLabel("Categoría:"));
        panel.add(comboCategoria);
        panel.add(btnAgregar);
        panel.add(btnEditar);
        panel.add(new JLabel("Buscar categoría:"));
        panel.add(comboBuscarCategoria);
        panel.add(btnBuscar);
        panel.add(btnOrdenar);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarRobot());
        btnBuscar.addActionListener(e -> buscarPorCategoria());
        btnEditar.addActionListener(e -> editarRobotPorId());
        btnOrdenar.addActionListener(e -> ordenarPorId());

        mostrarRobots();
    }

    private void inicializarDatos() {
        listaRobots.add(new Robot(1, "ALPHA", 80, 100, "DRON"));
        listaRobots.add(new Robot(20, "OMEGA", 40, 55, "SUBMARINO"));
        listaRobots.add(new Robot(150, "TITAN", 90, 80, "TERRESTRE"));
        listaRobots.add(new Robot(95, "NEBULA", 60, 60, "AÉREO"));
    }

    private void mostrarRobots() {
        textArea.setText("");
        for (Robot r : listaRobots) {
            textArea.append(r.toString() + "\n");
        }
    }

    private void agregarRobot() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            int energia = (int) comboEnergia.getSelectedItem();
            int puntaje = Integer.parseInt(txtPuntaje.getText());
            String categoria = (String) comboCategoria.getSelectedItem();

            if (puntaje <= 0 || puntaje % 5 != 0) {
                JOptionPane.showMessageDialog(this, "Puntaje inválido.");
                return;
            }

            boolean reemplazado = false;
            for (Robot r : listaRobots) {
                if (r.getId() == id) {
                    r.setNombre(nombre);
                    r.setEnergia(energia);
                    r.setPuntaje(puntaje);
                    r.setCategoria(categoria);
                    reemplazado = true;
                    break;
                }
            }

            if (!reemplazado) {
                listaRobots.add(new Robot(id, nombre, energia, puntaje, categoria));
            }

            mostrarRobots();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos.");
        }
    }

    private void buscarPorCategoria() {
        String categoria = (String) comboBuscarCategoria.getSelectedItem();
        boolean encontrado = false;
        textArea.setText("");
        for (Robot r : listaRobots) {
            if (r.getCategoria().equals(categoria)) {
                int nuevaEnergia = Math.min(r.getEnergia() + 20, 100);
                r.setEnergia(nuevaEnergia);
                textArea.append(r.toString() + "\n");
                encontrado = true;
            }
        }
        if (!encontrado) {
            textArea.setText("No se encontraron robots de esa categoría.");
        }
    }

    private void editarRobotPorId() {
        try {
            int id = Integer.parseInt(txtId.getText());
            boolean encontrado = false;
            for (Robot r : listaRobots) {
                if (r.getId() == id) {
                    r.setNombre(txtNombre.getText());
                    r.setEnergia((int) comboEnergia.getSelectedItem());
                    int puntaje = Integer.parseInt(txtPuntaje.getText());
                    if (puntaje <= 0 || puntaje % 5 != 0) {
                        JOptionPane.showMessageDialog(this, "Puntaje inválido.");
                        return;
                    }
                    r.setPuntaje(puntaje);
                    r.setCategoria((String) comboCategoria.getSelectedItem());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Robot no encontrado.");
            } else {
                mostrarRobots();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void ordenarPorId() {
        listaRobots.sort(Comparator.comparingInt(Robot::getId));
        mostrarRobots();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestionRobots().setVisible(true));
    }
}
