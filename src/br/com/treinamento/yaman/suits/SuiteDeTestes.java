package br.com.treinamento.yaman.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.treinamento.yaman.tests.Mobile;
import br.com.treinamento.yaman.tests.Rest;
import br.com.treinamento.yaman.tests.Soap;
import br.com.treinamento.yaman.tests.Web;

@RunWith(Suite.class)
@Suite.SuiteClasses({Web.class, Rest.class, Soap.class, Mobile.class})
public class SuiteDeTestes {
}

