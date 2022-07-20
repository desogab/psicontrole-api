import { Request, Response } from "express";

import { UpdateProfessionalInfoUseCase } from "./UpdateProfessionalInfoUseCase";

export class UpdateProfessionalInfoController {
  async handle(request: Request, response: Response) {
    const {
      name,
      birthdate,
      cpf,
      phone,
      professionalTitle,
      professionalDocument,
    } = request.body;
    const { professionalUserId } = request;

    const updateProfessionalInfoUseCase = new UpdateProfessionalInfoUseCase();

    await updateProfessionalInfoUseCase.execute({
      name,
      birthdate,
      cpf,
      phone,
      professionalTitle,
      professionalDocument,
      professionalUserId,
    });

    return response.status(204).send();
  }
}
