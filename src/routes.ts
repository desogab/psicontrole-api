import { Router } from "express";

import { CreateProfessionalUseController } from "./modules/professional/useCases/createProfessionalUser/CreateProfessionalUserController";

const routes = Router();

const createProfessionalUser = new CreateProfessionalUseController();

routes.post("/professional/create", createProfessionalUser.handle);

export { routes };
