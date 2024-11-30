package main.java.com.example.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.com.example.database.DatabaseConnector;
import main.java.com.example.model.Candidato;
import main.java.com.example.model.Curriculo;
import main.java.com.example.model.Vaga;

public class VagaRepository {

    private static final Logger logger = Logger.getLogger(VagaRepository.class.getName());

    public void adicionarVaga(Vaga vaga) throws SQLException {
        String query = "INSERT INTO vaga (empresa_id, titulo, descricao, cargo, carga_horaria, salario, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vaga.getEmpresaId());
            stmt.setString(2, vaga.getTitulo());
            stmt.setString(3, vaga.getDescricao());
            stmt.setString(4, vaga.getCargo());
            stmt.setString(5, vaga.getCargaHoraria());
            stmt.setDouble(6, vaga.getSalario());
            stmt.setString(7, "disponivel"); 

            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao adicionar vaga", e);
            throw e;
        }
    }

    public void removerVaga(int vagaId) throws SQLException {
        String query = "DELETE FROM vaga WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vagaId);
            stmt.executeUpdate();
            System.out.println("Vaga removida com sucesso!");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao remover vaga", e);
            throw e;
        }
    }

    public List<Vaga> getVagasDisponiveis() throws SQLException {
        List<Vaga> vagas = new ArrayList<>();
        String query = "SELECT v.id, v.titulo, v.descricao, v.cargo, v.carga_horaria, v.salario, v.status, e.nome AS nome_empresa " +
                       "FROM vaga v " +
                       "JOIN empresa e ON v.empresa_id = e.id " +
                       "WHERE LOWER(v.status) = 'disponivel'";  

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            boolean temVagas = false;
            while (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setId(rs.getInt("id"));
                vaga.setTitulo(rs.getString("titulo"));
                vaga.setDescricao(rs.getString("descricao"));
                vaga.setCargo(rs.getString("cargo"));
                vaga.setCargaHoraria(rs.getString("carga_horaria"));
                vaga.setSalario(rs.getDouble("salario"));
                vaga.setStatus(rs.getString("status"));
                vaga.setNomeEmpresa(rs.getString("nome_empresa"));
                vagas.add(vaga);
                temVagas = true; 
            }

            if (!temVagas) {
                System.out.println("Nenhuma vaga disponível encontrada.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar vagas", e);
            throw e;
        }
        return vagas;
    }

    public List<Vaga> listarVagasPorEmpresa(int empresaId) throws SQLException {
        String query = "SELECT id, titulo, descricao, cargo, carga_horaria, salario, status FROM vaga WHERE empresa_id = ?";
        List<Vaga> vagas = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, empresaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vaga vaga = new Vaga(
                        rs.getInt("id"),
                        empresaId,
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getString("cargo"),
                        rs.getString("carga_horaria"),
                        rs.getDouble("salario"),
                        rs.getString("status")
                    );
                    vagas.add(vaga);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar vagas por empresa", e);
            throw e;
        }
        return vagas;
    }

    public void adicionarCandidato(int candidatoLogadoId, int vagaId) throws SQLException {
        String statusQuery = "SELECT status FROM vaga WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statusStmt = connection.prepareStatement(statusQuery)) {

            statusStmt.setInt(1, vagaId);
            try (ResultSet rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    String status = rs.getString("status");
                    if ("preenchida".equalsIgnoreCase(status)) {
                        System.out.println("\nErro: Não é possível se candidatar a uma vaga fechada.\n");
                        return;
                    }
                } else {
                    System.out.println("\nErro: Vaga não encontrada.\n");
                    return;
                }
            }
        }

        String checkQuery = "SELECT COUNT(*) FROM candidatura WHERE candidato_id = ? AND vaga_id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {

            checkStmt.setInt(1, candidatoLogadoId);
            checkStmt.setInt(2, vagaId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("\nVocê já se candidatou a esta vaga.\n");
                    return; 
                }

                String insertQuery = "INSERT INTO candidatura (candidato_id, vaga_id) VALUES (?, ?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, candidatoLogadoId);
                    insertStmt.setInt(2, vagaId);
                    insertStmt.executeUpdate();
                    System.out.println("\nCandidatura registrada com sucesso!");
                }
            }
        }
    }


    public void removerCandidatura(int candidatoLogadoId, int vagaId) throws SQLException {
        String query = "DELETE FROM candidatura WHERE candidato_id = ? AND vaga_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, candidatoLogadoId);
            stmt.setInt(2, vagaId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nCandidatura removida com sucesso!");
            } else {
                System.out.println("Nenhuma candidatura encontrada.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao remover candidatura", e);
            throw e;
        }
    }
    
    public boolean existeCandidatoAplicado(int vagaId, int candidatoId) throws SQLException {
        String query = "SELECT COUNT(*) FROM candidatura WHERE vaga_id = ? AND candidato_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, vagaId);
            stmt.setInt(2, candidatoId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
    
    public String consultarStatusVaga(int vagaId) throws SQLException {
        String sql = "SELECT status FROM vaga WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, vagaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao consultar status da vaga", e);
            throw e;
        }
        return "Status não encontrado";
    }
    
    public List<Candidato> listarCandidatosAplicados(int vagaId) throws SQLException {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT c.id, c.nome, c.email, cu.id AS curriculo_id, cu.experiencia_profissional, cu.formacao_academica, cu.habilidades " +
                     "FROM candidato c " +
                     "JOIN candidatura vc ON c.id = vc.candidato_id " +
                     "LEFT JOIN curriculo cu ON c.id = cu.candidato_id " +
                     "WHERE vc.vaga_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, vagaId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Candidato candidato = new Candidato();
                    candidato.setId(rs.getInt("id"));
                    candidato.setNome(rs.getString("nome"));
                    candidato.setEmail(rs.getString("email"));

                    Curriculo curriculo = null;
                    int curriculoId = rs.getInt("curriculo_id");
                    if (curriculoId != 0) {
                        curriculo = new Curriculo();
                        curriculo.setId(curriculoId);
                        curriculo.setExperienciaProfissional(rs.getString("experiencia_profissional"));
                        curriculo.setFormacaoAcademica(rs.getString("formacao_academica"));
                        curriculo.setHabilidades(rs.getString("habilidades"));
                    }

                    candidato.setCurriculo(curriculo);

                    candidatos.add(candidato);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar candidatos", e);
            throw e;
        }
        return candidatos;
    }

    public String consultarStatusCandidatura(int candidatoLogadoId, int vagaId) throws SQLException {
        String query = "SELECT status FROM candidatura WHERE candidato_id = ? AND vaga_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, candidatoLogadoId);
            stmt.setInt(2, vagaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status");
                } else {
                    return "Sem candidatura registrada.";
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao consultar status da candidatura", e);
            throw e;
        }
    }
    
    public void selecionarCandidato(int vagaId, int candidatoId) throws SQLException {
        String updateVagaStatus = "UPDATE vaga SET status = 'preenchida' WHERE id = ?";
        String aprovarCandidato = "UPDATE candidatura SET status = 'selecionado' WHERE vaga_id = ? AND candidato_id = ?";
        String reprovarOutros = "UPDATE candidatura SET status = 'rejeitado' WHERE vaga_id = ? AND candidato_id != ?";

        try (Connection conn = DatabaseConnector.getConnection()) {
            try {
                conn.setAutoCommit(false); 

                try (PreparedStatement stmt = conn.prepareStatement(updateVagaStatus)) {
                    stmt.setInt(1, vagaId);
                    stmt.executeUpdate();
                }

                try (PreparedStatement stmt = conn.prepareStatement(aprovarCandidato)) {
                    stmt.setInt(1, vagaId);
                    stmt.setInt(2, candidatoId);
                    stmt.executeUpdate();
                }

                try (PreparedStatement stmt = conn.prepareStatement(reprovarOutros)) {
                    stmt.setInt(1, vagaId);
                    stmt.setInt(2, candidatoId);
                    stmt.executeUpdate();
                }

                conn.commit(); 
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
