package app.br.psicontroleapi.entity;

import app.br.psicontroleapi.vo.Cpf;

import java.util.Objects;

public class Person {
    private final String name;
    private final Cpf cpf;

    public Person(String name, String cpf) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank");
        this.name = name;
        this.cpf = new Cpf(cpf);
    }

    public String getCpf() {
        return cpf.value();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(cpf, person.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cpf=" + cpf +
                '}';
    }
}
