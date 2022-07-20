import { Request, Response } from "express";

import { AuthProfessionalUserUseCase } from "./AuthProfessionalUserUseCase";

export class AuthProfessionalUserController {
  async handle(request: Request, response: Response) {
    const { email, password } = request.body;

    const authProfessionalUserUseCase = new AuthProfessionalUserUseCase();

    const result = await authProfessionalUserUseCase.execute({
      email,
      password,
    });

    return response.json(result);
  }
}
