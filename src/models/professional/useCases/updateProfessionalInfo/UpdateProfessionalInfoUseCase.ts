import { prisma } from "../../../../database/prismaClient";

interface IProfessionalInfo {
  name?: string;
  birthdate?: Date;
  cpf?: string;
  phone?: string;
  professionalTitle?: string;
  professionalDocument?: string;
  professionalUserId: string;
}

export class UpdateProfessionalInfoUseCase {
  async execute({
    name,
    birthdate,
    cpf,
    phone,
    professionalTitle,
    professionalDocument,
    professionalUserId,
  }: IProfessionalInfo) {
    const updateInfo = await prisma.professionalInfo.update({
      where: {
        professionalUserId,
      },
      data: {
        name,
        birthdate: new Date(birthdate),
        cpf,
        phone,
        professionalTitle,
        professionalDocument,
      },
    });

    return updateInfo;
  }
}
