import { prisma } from "../../../../database/prismaClient";
import { AppError } from "../../../../errors/AppError";

interface ICreateClient {
  active?: boolean;
  sponsor?: boolean;
  name: string;
  birthdate?: string;
  cpf: string;
  email?: string;
  phone: string;
  consultationPrice: number;
  professionalUserId: string;
}

export class CreateClientUseCase {
  async execute({
    active,
    sponsor,
    name,
    birthdate,
    cpf,
    phone,
    email,
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
        active,
        sponsor,
        name,
        birthdate,
        cpf,
        phone,
        email,
        consultationPrice,
        professionalUserId,
      },
    });

    return client;
  }
}
