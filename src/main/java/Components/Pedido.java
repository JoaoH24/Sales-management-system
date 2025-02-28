package Components;

/**
 *
 * @author Joao
 */
public class Pedido {
    private int id;
    private int empleado_id;
    private int cliente_id;
    private String nombreEmpleado;
    private String nombreCliente;
    private String fechaPedido;
    private String estado;

    public Pedido(int id, int empleado_id, int cliente_id, String nombreEmpleado, String nombreCliente, String fechaPedido, String estado) {
        this.id = id;
        this.empleado_id = empleado_id;
        this.cliente_id = cliente_id;
        this.nombreEmpleado = nombreEmpleado;
        this.nombreCliente = nombreCliente;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
