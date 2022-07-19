import { prisma } from "../../../../database/prismaClient";

interface ICreateClient {
  name: string;
  cpf: string;
  phone: string;
  consultationPrice: number;
  professionalUserId: string;
}

export class CreateClientUseCase {
  async execute({
    name,
    cpf,
    phone,
    consultationPrice,
    professionalUserId,
  }: ICreateClient) {
    const clientAlreadyExists = await prisma.client.findFirst({
      where: {
        cpf: {
          equals: cpf,
        },
      },
    });

    if (clientAlreadyExists) throw new Error("O cliente já existe!");

    const client = await prisma.client.create({
      data: {
        name,
        cpf,
        phone,
        consultationPrice,
        professionalUserId,
      },
    });

    return client;
  }
}
