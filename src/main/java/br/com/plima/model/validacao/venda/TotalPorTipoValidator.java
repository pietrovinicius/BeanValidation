package br.com.plima.model.validacao.venda;

import br.com.plima.model.TipoVenda;
import br.com.plima.model.Venda;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TotalPorTipoValidator implements ConstraintValidator<TotalPorTipo, Venda> {

    @Override
    public void initialize(TotalPorTipo totalPorTipo) {

    }

    @Override
    public boolean isValid(Venda venda, ConstraintValidatorContext context) {
        //definicao das regras de que a venda é valida

        //se for vazio já vai invalidar
        if (venda == null) return false;

        boolean valido = false;

        if (venda.getTipo() == TipoVenda.VendaPadrao && venda.getTotal() > 0){
            valido = true;
        }

        if (venda.getTipo() == TipoVenda.VendaBrinde && venda.getTotal() == 0){
            valido = true;
        }

        //adicionaro nome da propriedade ao contexto, pra pegar essa informação ao executar o codigo
        if (!valido) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("tipo").addConstraintViolation();
        }

        return valido;
    }
}
