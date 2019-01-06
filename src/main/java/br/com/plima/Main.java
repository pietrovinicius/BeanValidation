package br.com.plima;

import br.com.plima.model.ItemVenda;
import br.com.plima.model.TipoVenda;
import br.com.plima.model.Venda;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ItemVenda item = new ItemVenda();
        item.setDescricao("Camisa regata");
        item.setPreco(10);
        item.setQuantidade(1);

        Venda venda = new Venda();
        venda.setData(Calendar.getInstance().getTime());
        venda.setTipo(TipoVenda.VendaPadrao);
        venda.setTotal(0);
        venda.setItens(new ArrayList<ItemVenda>() {{
            add(item);
        }});

        //verificação de valores válidos
        Set<ConstraintViolation<Venda>> violation = validator.validate(venda);
        //pecorrendo o obejto violation pra escrever todos os erros
        violation.stream().map(constraintViolation -> "Erro: "
                + constraintViolation.getMessage()
                + " ["
                + constraintViolation.getRootBeanClass().getSimpleName()
                + ", "
                + constraintViolation.getPropertyPath()
                + "]"
        ).forEachOrdered(System.out::println);
    }
}
