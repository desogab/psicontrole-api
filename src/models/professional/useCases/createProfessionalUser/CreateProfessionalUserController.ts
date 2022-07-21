import { Request, Response } from "express";

import { CreateProfessionalUserUseCase } from "./CreateProfessionalUserUseCase";

export class CreateProfessionalUseController {
  async handle(request: Request, response: Response) {
    const { email, password, name } = request.body;

    const createProfessionalUseCase = new CreateProfessionalUserUseCase();

    const professional = await createProfessionalUseCase.execute({
      email,
      password,
      name,
    });

    return response.status(201).json({
      id: professional.id,
      email: professional.email,
    });
  }
}
