import { prisma } from "../../../../database/prismaClient";
import { AppError } from "../../../../errors/AppError";

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

    if (clientAlreadyExists) throw new AppError("O cliente já existe!", 400);

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
