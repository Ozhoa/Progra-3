import java.util.LinkedList;
import java.util.Queue;

public class ColaRobot {
    private Queue<Robot> colaRobots;

    public ColaRobot() {
        colaRobots = new LinkedList<>();
    }


    // Método para encolar un robot personalizado
    public void encolarRobotPersonalizado(Robot robot) {
        colaRobots.add(robot);
    }

    // R1: Encolar robots
    public void encolarRobots() {
        colaRobots.clear();
        colaRobots.add(new Robot()); // Constructor por defecto
        colaRobots.add(new Robot(1, "Optimus", "Autobot", 200, "Liderar"));
        colaRobots.add(new Robot(2, "Bumblebee", "Autobot", 150, "Volar"));
        colaRobots.add(new Robot(3, "Megatron", "Decepticon", 300, "Liderar"));
        colaRobots.add(new Robot(4, "Starscream", "Decepticon", 180, "Volar"));
    }

    // R2: Calcular valor de ataque
    public String calcularValorAtaque() {
        StringBuilder sb = new StringBuilder("Cálculo de Valor de Ataque:\n");
        for (Robot robot : colaRobots) {
            int valorAtaque = robot.getPoder();
            if (robot.getFaccion().equalsIgnoreCase("Autobot")) {
                valorAtaque += valorAtaque * 0.25;
            } else if (robot.getFaccion().equalsIgnoreCase("Decepticon")) {
                valorAtaque += valorAtaque * 0.10;
            }
            sb.append(robot).append(", Valor de Ataque: ").append(valorAtaque).append("\n");
        }
        return sb.toString();
    }

    // R3: Copiar robots por función
    public String copiarPorFuncion(String funcion) {
        Queue<Robot> nuevaCola = new LinkedList<>();
        for (Robot robot : colaRobots) {
            if (robot.getFuncion().equalsIgnoreCase(funcion)) {
                nuevaCola.add(robot);
            }
        }

        StringBuilder sb = new StringBuilder("Cola Original:\n");
        for (Robot robot : colaRobots) {
            sb.append(robot).append("\n");
        }
        sb.append("\nNueva Cola (Función: ").append(funcion).append("):\n");
        for (Robot robot : nuevaCola) {
            sb.append(robot).append("\n");
        }
        return sb.toString();
    }

    public String mostrarCola() {
        StringBuilder sb = new StringBuilder("Robots en la cola:\n");
        for (Robot robot : colaRobots) {
            sb.append(robot).append("\n");
        }
        return sb.toString();
    }
}