/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufs.dcomp.idrug.constantes;

/**
 *
 * @author thiag
 */
public enum Excecao {

    USUARIO_SENHA_INVALIDO("Usuario ou senha é inválido", "IDR0001"),
    CPF_INVALIDO("O cpf informado é inválido", "IDR0002"),
    CNPJ_INVALIDO("O cnpj informado é inválido", "IDR0003"),
    NAO_FOI_POSSIVEL_CADASTRAR_USUARIO("Não foi possível cadastrar o usuário informado.", "IDR0004"),
    NAO_FOI_POSSIVEL_RESGATAR_MEDICAMENTOS("Não foi possível resgatar os medicamentos.", "IDR0005"),
    DADOS_INFORMADOS_INCORRETOS("Dados incorretos.", "IDR006"),
    ERRO_GENERICO("Não foi possível concluir a operação solicitada.", "IDR0007"),
    PACIENTE_NAO_LOCALIZADO("Paciente não localizado.", "IDR0008"),
    FARMACIA_NAO_LOCALIZADA("Farmacia não localizada.", "IDR0009"),
    COLETA_NAO_CONFIRMADA("A coleta não foi confirmada.", "IDR0010");

    public String mensagem;
    public String codigo;

    private Excecao(String mensagem, String codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
    }
}
