import { Request, Response } from "express";

import { CreateProfessionalUserUseCase } from "./CreateProfessionalUserUseCase";

export class CreateProfessionalUseController {
  async handle(request: Request, response: Response) {
    const { email, password } = request.body;

    const createProfessionalUseCase = new CreateProfessionalUserUseCase();

    const professional = await createProfessionalUseCase.execute({
      email,
      password,
    });

    return response.status(201).json(professional);
  }
}
