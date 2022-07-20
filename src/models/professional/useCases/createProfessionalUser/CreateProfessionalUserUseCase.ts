import { hash } from "bcrypt";

import { prisma } from "../../../../database/prismaClient";
import { AppError } from "../../../../errors/AppError";

interface ICreateProfessionalUser {
  email: string;
  password: string;
}

export class CreateProfessionalUserUseCase {
  async execute({ email, password }: ICreateProfessionalUser) {
    const professionalAlreadyExists = await prisma.professionalUser.findFirst({
      where: {
        email: {
          equals: email,
        },
      },
    });

    if (professionalAlreadyExists)
      throw new AppError(
        "O email já se encontra em nosso banco de dados, tente novamente.",
        400
      );

    const hasPassword = await hash(password, 10);

    const client = await prisma.professionalUser.create({
      data: {
        email,
        password: hasPassword,
        ProfessionalInfo: {
          create: {},
        },
      },
    });

    return client;
  }
}
