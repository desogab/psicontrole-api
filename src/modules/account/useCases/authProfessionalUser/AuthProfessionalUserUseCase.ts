import { compare } from "bcrypt";
import { sign } from "jsonwebtoken";

import { prisma } from "../../../../database/prismaClient";

interface IAuthProfessionalUser {
  email: string;
  password: string;
}

export class AuthProfessionalUserUseCase {
  async execute({ email, password }: IAuthProfessionalUser) {
    const professionalUser = await prisma.professionalUser.findFirst({
      where: {
        email,
      },
    });

    if (!professionalUser) throw new Error("Email or password is invalid!");

    const passwordMatch = await compare(password, professionalUser.password);

    if (!passwordMatch) throw new Error("Email or password is invalid!");

    const token = sign({ email }, process.env.SECRET_JWT_TOKEN as string, {
      subject: professionalUser.id,
      expiresIn: "8h",
    });

    return {
      access_token: token,
    };
  }
}
