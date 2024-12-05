import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class ListaCircularGUI {
    private JTextField textField1;
    private JTextArea txtResultado;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton visualizarButton;
    private JPanel pGeneral;

    private final ListaCircular lista;

    public ListaCircularGUI() {
        lista = new ListaCircular();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField1.getText();
                if (!input.isEmpty()) {
                    try {
                        int valor = Integer.parseInt(input);
                        lista.agregarElemento(valor);
                        txtResultado.append("Elemento " + valor + " añadido.\n");
                        textField1.setText("");
                    } catch (NumberFormatException ex) {
                        txtResultado.append("Por favor, introduce un número válido.\n");
                    }
                } else {
                    txtResultado.append("Por favor, introduce un valor.\n");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField1.getText();
                if (!input.isEmpty()) {
                    try {
                        int valor = Integer.parseInt(input);
                        lista.removerElemento(valor);
                        txtResultado.append("Elemento " + valor + " eliminado.\n");
                        textField1.setText("");
                    } catch (NumberFormatException ex) {
                        txtResultado.append("Por favor, introduce un número válido.\n");
                    }
                } else {
                    txtResultado.append("Por favor, introduce un valor.\n");
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField1.getText();
                if (!input.isEmpty()) {
                    try {
                        int valor = Integer.parseInt(input);
                        NodoLista resultado = lista.buscarElemento(valor, Comparator.naturalOrder());
                        if (resultado != null) {
                            txtResultado.append("Elemento " + valor + " encontrado.\n");
                        } else {
                            txtResultado.append("Elemento " + valor + " no encontrado.\n");
                        }
                        textField1.setText("");
                    } catch (NumberFormatException ex) {
                        txtResultado.append("Por favor, introduce un número válido.\n");
                    }
                } else {
                    txtResultado.append("Por favor, introduce un valor.\n");
                }
            }
        });

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtResultado.append("Contenido de la lista:\n");
                NodoLista actual = lista.obtenerInicio(); // Usamos el getter
                if (actual != null) {
                    do {
                        txtResultado.append(actual.valor + " ");
                        actual = actual.siguiente;
                    } while (actual != lista.obtenerInicio());
                    txtResultado.append("\n");
                } else {
                    txtResultado.append("La lista está vacía.\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lista Circular GUI");
        frame.setContentPane(new ListaCircularGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
