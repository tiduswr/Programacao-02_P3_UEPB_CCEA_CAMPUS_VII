package com.uepb.hospital;

import java.util.Date;

public class ProfissionalSaude extends Pessoa {
    private int cargo; // 1 - Enfermeiro, 2 - Medico

    public ProfissionalSaude(String nome, String cpf, Date dtNasc, char sexo, Endereco endereco, Contato contato,
                    int cargo){
        super(nome, cpf, dtNasc, sexo, endereco, contato);
        this.cargo = cargo;
    }
    public ProfissionalSaude(){
    }
    
    public String getCargo(){
        return switch (this.cargo) {
            case 1 -> "Enfermeiro";
            case 2 -> "Médico";
            default -> "Outro";
        };
    }
    public void setCargo(int indexCargo){
        this.cargo = indexCargo;
    }
}
