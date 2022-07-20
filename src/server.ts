import { PrismaClientKnownRequestError } from "@prisma/client/runtime";
import express, { NextFunction, Request, Response } from "express";

import "express-async-errors";
import { AppError } from "./errors/AppError";
import { routes } from "./routes";

const app = express();

app.use(express.json());

app.use(routes);

app.use(
  (err: Error, request: Request, response: Response, next: NextFunction) => {
    if (err instanceof AppError) {
      return response.status(err.statusCode).json({
        message: err.message,
      });
    }

    if (err instanceof PrismaClientKnownRequestError) {
      const errorMessage = err.meta.target[0];

      return response.status(400).json({
        code: err.code,
        message: `O campo ${errorMessage} já existe.`,
      });
    }

    return response.status(500).json({
      status: "error",
      message: `Internal Server Error ${err.message}`,
    });
  }
);

app.listen(3003, () => console.log("Server ins running..."));
