import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestionPersonajes extends JFrame {
    private ArrayList<Personaje> personajes;
    private JTextArea areaPersonajes;
    private JComboBox<String> comboNombres;
    private JComboBox<String> comboClases;
    private JComboBox<String> comboRareza;
    private JTextField txtId, txtFuerza;
    private JButton btnAgregar, btnBuscar, btnOrdenar, btnEliminar;

    public GestionPersonajes() {
        personajes = new ArrayList<>();
        inicializarDatos();
        inicializarInterfaz();
        setLocationRelativeTo(null);
    }

    private void inicializarDatos() {
        personajes.add(new Personaje(5, "Link", "Arquero", 300, "Épico"));
        personajes.add(new Personaje(15, "Kratos", "Guerrero", 500, "Legendario"));
        personajes.add(new Personaje(30, "Aloy", "Arquero", 400, "Épico"));
        personajes.add(new Personaje(50, "Geralt", "Mago", 600, "Raro"));
        actualizarLista();
    }

    private void actualizarLista() {
        StringBuilder lista = new StringBuilder();
        lista.append("Lista de Personajes:\n\n");
        for (Personaje p : personajes) {
            lista.append(p.toString()).append("\n");
        }
        if (areaPersonajes != null) {
            areaPersonajes.setText(lista.toString());
        }
    }

    private void inicializarInterfaz() {
        setTitle("Gestión de Personajes de Videojuego");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(6, 2, 5, 5));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] nombres = {"Link", "Kratos", "Aloy", "Geralt"};
        String[] clases = {"Guerrero", "Arquero", "Mago"};
        String[] rarezas = {"Común", "Raro", "Épico", "Legendario"};

        txtId = new JTextField();
        comboNombres = new JComboBox<>(nombres);
        comboClases = new JComboBox<>(clases);
        txtFuerza = new JTextField();
        comboRareza = new JComboBox<>(rarezas);

        panelEntrada.add(new JLabel("ID (múltiplo de 5):"));
        panelEntrada.add(txtId);
        panelEntrada.add(new JLabel("Nombre:"));
        panelEntrada.add(comboNombres);
        panelEntrada.add(new JLabel("Clase:"));
        panelEntrada.add(comboClases);
        panelEntrada.add(new JLabel("Fuerza (múltiplo de 100):"));
        panelEntrada.add(txtFuerza);
        panelEntrada.add(new JLabel("Rareza:"));
        panelEntrada.add(comboRareza);

        // Botones
        btnAgregar = new JButton("Agregar Personaje");
        btnBuscar = new JButton("Buscar por Nombre");
        btnOrdenar = new JButton("Ordenar por Fuerza");
        btnEliminar = new JButton("Eliminar Personaje");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnOrdenar);
        panelBotones.add(btnEliminar);

        // Área de texto
        areaPersonajes = new JTextArea();
        areaPersonajes.setEditable(false);
        areaPersonajes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaPersonajes);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Información de Personajes"));

        // Añadir componentes al frame
        add(panelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        configurarEventos();
        actualizarLista();
    }

    private void configurarEventos() {
        btnAgregar.addActionListener(e -> agregarPersonaje());
        btnBuscar.addActionListener(e -> buscarPersonajePorNombre());
        btnOrdenar.addActionListener(e -> ordenarPorFuerza());
        btnEliminar.addActionListener(e -> eliminarPersonaje());
    }

    private void agregarPersonaje() {
        try {
            int id = Integer.parseInt(txtId.getText());
            int fuerza = Integer.parseInt(txtFuerza.getText());

            // Validación de ID único
            for (Personaje p : personajes) {
                if (p.getId() == id) {
                    JOptionPane.showMessageDialog(this,
                            "Error: El ID ya existe",
                            "Error de Validación",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Validación de múltiplos
            if (id % 5 != 0) {
                JOptionPane.showMessageDialog(this,
                        "Error: El ID debe ser múltiplo de 5",
                        "Error de Validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (fuerza % 100 != 0) {
                JOptionPane.showMessageDialog(this,
                        "Error: La fuerza debe ser múltiplo de 100",
                        "Error de Validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Personaje nuevo = new Personaje(
                    id,
                    comboNombres.getSelectedItem().toString(),
                    comboClases.getSelectedItem().toString(),
                    fuerza,
                    comboRareza.getSelectedItem().toString()
            );
            personajes.add(nuevo);
            actualizarLista();
            limpiarCampos();
            JOptionPane.showMessageDialog(this,
                    "Personaje agregado exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: ID y Fuerza deben ser números válidos",
                    "Error de Entrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPersonaje() {
        try {
            String idStr = JOptionPane.showInputDialog(this,
                    "Ingrese el ID del personaje a eliminar:",
                    "Eliminar Personaje",
                    JOptionPane.QUESTION_MESSAGE);

            if (idStr == null) return; // Usuario canceló

            int idEliminar = Integer.parseInt(idStr);
            boolean encontrado = false;

            for (int i = 0; i < personajes.size(); i++) {
                if (personajes.get(i).getId() == idEliminar) {
                    Personaje eliminado = personajes.remove(i);
                    JOptionPane.showMessageDialog(this,
                            "Personaje eliminado: " + eliminado.getNombre(),
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    encontrado = true;
                    actualizarLista();
                    break;
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró un personaje con el ID: " + idEliminar,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un ID válido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtFuerza.setText("");
        comboNombres.setSelectedIndex(0);
        comboClases.setSelectedIndex(0);
        comboRareza.setSelectedIndex(0);
    }

    private void buscarPersonajePorNombre() {
        String nombreBuscado = comboNombres.getSelectedItem().toString();
        StringBuilder resultado = new StringBuilder();
        resultado.append("Resultados de la búsqueda para: ").append(nombreBuscado).append("\n\n");
        boolean encontrado = false;

        for (Personaje p : personajes) {
            if (p.getNombre().equals(nombreBuscado)) {
                resultado.append(p.toString()).append("\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            resultado.append("No se encontraron personajes con ese nombre");
        }

        areaPersonajes.setText(resultado.toString());
    }

    private void ordenarPorFuerza() {
        personajes.sort((p1, p2) -> p2.getFuerza() - p1.getFuerza());
        StringBuilder resultado = new StringBuilder();
        resultado.append("Personajes ordenados por fuerza (descendente):\n\n");
        for (Personaje p : personajes) {
            resultado.append(p.toString()).append("\n");
        }
        areaPersonajes.setText(resultado.toString());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new GestionPersonajes().setVisible(true);
        });
    }
}