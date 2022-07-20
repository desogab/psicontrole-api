import { Request, Response } from "express";

import { CreateClientUseCase } from "./CreateClientUseCase";

export class CreateClientController {
  async handle(request: Request, response: Response) {
    const {
      active,
      sponsor,
      name,
      birthdate,
      cpf,
      phone,
      email,
      consultationPrice,
    } = request.body;
    const { professionalUserId } = request;

    const createClientUseCase = new CreateClientUseCase();

    const client = await createClientUseCase.execute({
      active,
      sponsor,
      name,
      birthdate,
      cpf,
      phone,
      email,
      consultationPrice,
      professionalUserId,
    });

    return response.status(201).json(client);
  }
}
