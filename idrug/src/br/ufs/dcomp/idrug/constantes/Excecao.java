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

    USUARIO_SENHA_INVALIDO("Usuario ou senha é inválido", "IDR001"),
    CPF_INVALIDO("O cpf informado é inválido", "IDR002"),
    CNPJ_INVALIDO("O cpf informado é inválido", "IDR003"),
    NAO_FOI_POSSIVEL_CADASTRAR_USUARIO("Não foi possível cadastrar o usuário informado.", "IDR004");

    public String mensagem;
    public String codigo;

    private Excecao(String mensagem, String codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
    }
}
