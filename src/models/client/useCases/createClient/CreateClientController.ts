import { Request, Response } from "express";

import { CreateClientUseCase } from "./CreateClientUseCase";

export class CreateClientController {
  async handle(request: Request, response: Response) {
    const { name, cpf, phone, consultationPrice } = request.body;
    const { professionalUserId } = request;

    const createClientUseCase = new CreateClientUseCase();

    const client = await createClientUseCase.execute({
      name,
      cpf,
      phone,
      consultationPrice,
      professionalUserId,
    });

    return response.json(client);
  }
}
