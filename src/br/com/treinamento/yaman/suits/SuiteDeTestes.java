package br.com.treinamento.yaman.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.treinamento.yaman.tests.Example;
import br.com.treinamento.yaman.tests.Example2;

@RunWith(Suite.class)
@Suite.SuiteClasses({Example.class, Example2.class})
public class SuiteDeTestes {
}

