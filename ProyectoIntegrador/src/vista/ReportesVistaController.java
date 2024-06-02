package vista;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportesVistaController {

    @FXML
    private PieChart pieChartComisiones;

    @FXML
    private BarChart<String, Number> barChartVentas;

    @FXML
    private LineChart<String, Number> lineChartTendencia;

    @FXML
    private void generarReportes() {
        // Limpiar gráficos existentes
        pieChartComisiones.getData().clear();
        barChartVentas.getData().clear();
        lineChartTendencia.getData().clear();

        // Generar datos para el gráfico de pie
        generarDatosPieChart();

        // Generar datos para el gráfico de barras
        generarDatosBarChart();

        // Generar datos para el gráfico de líneas
        generarDatosLineChart();
    }

    private void generarDatosPieChart() {
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "usuario", "contraseña")) {
            String query = "SELECT vendedor, SUM(comision) AS total_comision FROM ventas GROUP BY vendedor";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String vendedor = rs.getString("vendedor");
                double totalComision = rs.getDouble("total_comision");
                PieChart.Data data = new PieChart.Data(vendedor, totalComision);
                pieChartComisiones.getData().add(data);
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo generar el gráfico de comisiones.");
        }
    }

    private void generarDatosBarChart() {
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "usuario", "contraseña")) {
            String query = "SELECT producto, SUM(cantidad) AS total_ventas FROM ventas GROUP BY producto";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Ventas por Producto");

            while (rs.next()) {
                String producto = rs.getString("producto");
                int totalVentas = rs.getInt("total_ventas");
                series.getData().add(new XYChart.Data<>(producto, totalVentas));
            }

            barChartVentas.getData().add(series);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo generar el gráfico de ventas por producto.");
        }
    }

    private void generarDatosLineChart() {
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "usuario", "contraseña")) {
            String query = "SELECT TO_CHAR(fecha, 'YYYY-MM') AS periodo, SUM(total) AS total_ventas FROM ventas GROUP BY TO_CHAR(fecha, 'YYYY-MM') ORDER BY TO_CHAR(fecha, 'YYYY-MM')";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Tendencia de Ventas");

            while (rs.next()) {
                String periodo = rs.getString("periodo");
                double totalVentas = rs.getDouble("total_ventas");
                series.getData().add(new XYChart.Data<>(periodo, totalVentas));
            }

            lineChartTendencia.getData().add(series);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo generar el gráfico de tendencia de ventas.");
        }
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR, contenido, ButtonType.OK);
        alert.setTitle(titulo);
        alert.showAndWait();
    }
}
