package br.com.fiap.dao;

import br.com.fiap.enums.StatusEnum;
import br.com.fiap.models.Tarefa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@ApplicationScoped
public class TarefaDao {

    private static final Logger LOGGER = Logger.getLogger(TarefaDao.class.getName());

    @Inject
    DataSource dataSource;
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM TAREFA");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getLong("id"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setDataLimite(rs.getDate("data_limite").toLocalDate());
                tarefa.setStatus(StatusEnum.valueOf(rs.getString("status")));
                tarefa.setResponsavel(rs.getString("responsavel"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            LOGGER.severe("Erro ao listar tarefas: " + e.getMessage());
        }
        return tarefas;
    }

    public Tarefa buscarPorId(Long id) {
        String sql = "SELECT * FROM TAREFA WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getLong("id"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setDataLimite(rs.getDate("data_limite").toLocalDate());
                    tarefa.setStatus(StatusEnum.valueOf(rs.getString("status")));
                    tarefa.setResponsavel(rs.getString("responsavel"));
                    tarefa.setTitulo(rs.getString("titulo"));
                    return tarefa;
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Erro ao buscar tarefa por ID: " + e.getMessage());
        }
        return null;
    }

    public void criar(Tarefa tarefa) {
        String sql = "INSERT INTO TAREFA (titulo, descricao, data_limite, status, responsavel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tarefa.getTitulo());
            ps.setString(2, tarefa.getDescricao());
            ps.setDate(3, java.sql.Date.valueOf(tarefa.getDataLimite()));
            ps.setString(4, tarefa.getStatus().name());
            ps.setString(5, tarefa.getResponsavel());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Erro ao criar tarefa: " + e.getMessage());
        }
    }

    public void atualizar(Tarefa tarefa) {
        String sql = "UPDATE TAREFA SET titulo = ?, descricao = ?, data_limite = ?, status = ?, responsavel = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tarefa.getTitulo());
            ps.setString(2, tarefa.getDescricao());
            ps.setDate(3, java.sql.Date.valueOf(tarefa.getDataLimite()));
            ps.setString(4, tarefa.getStatus().name());
            ps.setString(5, tarefa.getResponsavel());
            ps.setLong(6, tarefa.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM TAREFA WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe("Erro ao deletar tarefa: " + e.getMessage());
        }
    }
}
